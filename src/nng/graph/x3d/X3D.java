/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details. 
 */
package nng.graph.x3d;


import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import nng.graph.INode;
import nng.graph.models.Box;

import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class X3D {
	final static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	
	public static Document toX3D(INode rootNode) {
		Document doc = null;
		try {
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
			doc = db.newDocument();
			
			doc.setXmlVersion("1.0");
			
			Node x3dNode = makeX3DNode(doc);
			doc.appendChild(x3dNode);
			
			x3dNode.appendChild(makeHeadNode(doc));
			
			Node sceneNode = makeSceneNode(doc);
			sceneNode.appendChild(makeShapes(rootNode, doc));
			x3dNode.appendChild(sceneNode);
			
		} catch (Exception e) {
			
		}
		
		return doc;
	}
	
	private static Node makeHeadNode(Document doc) {
		Node head = doc.createElement("head");
		Node metaGenerator = doc.createElement("meta");
		NamedNodeMap metaGenAttributes = metaGenerator.getAttributes();
		
		Attr genName = doc.createAttribute("name");
		genName.setTextContent("generator");
		Attr genContent = doc.createAttribute("content");
		genContent.setTextContent("David Serrano's Java Scene Graph to X3D generator");
		
		metaGenAttributes.setNamedItem(genName);
		metaGenAttributes.setNamedItem(genContent);
		
		head.appendChild(metaGenerator);
		return head;
	}
	
	private static Node makeShapes(INode rootNode, Document doc) {
		List<INode> children = rootNode.getChildren();
		
		Node transformNode = transformNode(
				rootNode.getTranslation(),
				rootNode.getScale(),
				rootNode.getRotation(),
				doc);
		
		String name = rootNode.getName();
		if (name != null) {
			Comment nameComment = doc.createComment(name);
			transformNode.appendChild(nameComment);
		}
		
		if (children.size() > 0) {
			for (INode graphNode : children) {
				Node childNode = makeShapes(graphNode, doc);
				if (childNode != null) {
					transformNode.appendChild(childNode);
				}
			}
		} else if (rootNode.getShapeName().equals("Box")) {
			transformNode.appendChild(makeBox(rootNode.getTranslation(),
					rootNode.getScale(),
					rootNode.getRotation(), rootNode.getFillColor(), doc)); 
		}
		
		return transformNode;
	}
	
	private static Node transformNode(double[] translation, double[] scale,
			double[] rotation, Document doc) {
		
		Node transformNode = doc.createElement("Transform");
		NamedNodeMap tranformAttributes = transformNode.getAttributes();
		
		Attr transfTranslationAttr = doc.createAttribute("translation");
		transfTranslationAttr.setTextContent(
				translation[0] + " " + translation[1] + " " + translation[2]);
		
		Attr transfScaleAttr = doc.createAttribute("scale");
		transfScaleAttr.setTextContent(
				scale[0] + " " + scale[1] + " " + scale[2]);
		
		// FIXME: rotation is incorrect
		Attr transfRotationAttr = doc.createAttribute("rotation");
		transfRotationAttr.setTextContent("1 0 0 "
				+ Math.toRadians(rotation[0]));
		
		tranformAttributes.setNamedItem(transfTranslationAttr);
		tranformAttributes.setNamedItem(transfScaleAttr);
		tranformAttributes.setNamedItem(transfRotationAttr);
		
		return transformNode;
	}

	private static Node makeBox(double[] translation, double[] scale, double[] rotation, double[] color, Document doc) {
//		
//		Node transformNode = transformNode(
//				translation,
//				scale,
//				rotation,
//				doc);
		
		Node shapeNode = doc.createElement("Shape");
		Node appearanceNode = doc.createElement("Appearance");
		Node materialNode = doc.createElement("Material");
		NamedNodeMap matAttributes = materialNode.getAttributes();
		
		Attr matDiffuseColorAttr = doc.createAttribute("diffuseColor");
		matDiffuseColorAttr.setTextContent(
				color[0] + " " + color[1] + " " + color[2]);
		
		matAttributes.setNamedItem(matDiffuseColorAttr);
		appearanceNode.appendChild(materialNode);
		shapeNode.appendChild(appearanceNode);
		
		Node boxNode = doc.createElement("Box");
		shapeNode.appendChild(boxNode);

		return shapeNode;
		
//		transformNode.appendChild(shapeNode);

//		return transformNode;
	}
	
	private static Node makeSceneNode(Document doc) {
		Node sceneNode = doc.createElement("Scene");
		return sceneNode;
	}

	private static Node makeX3DNode(Document doc) {
		Node x3dNode = doc.createElement("X3D");
		NamedNodeMap x3DAttributes = x3dNode.getAttributes();
		
		Attr x3DVersionAttr = doc.createAttribute("version");
		x3DVersionAttr.setTextContent("3.1");
		Attr x3DProfileAttr = doc.createAttribute("profile");
		x3DProfileAttr.setTextContent("Interchange");
		
		x3DAttributes.setNamedItem(x3DVersionAttr);
		x3DAttributes.setNamedItem(x3DProfileAttr);
		
		return x3dNode;
	}

	public static String toX3DStr(INode node) {
		Document doc = toX3D(node);
		
		Transformer transformer;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "ISO//Web3D//DTD X3D 3.2//EN");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://www.web3d.org/specifications/x3d-3.2.dtd");

			//initialize StreamResult with File object to save to file
			StreamResult result = new StreamResult(new StringWriter());
			DOMSource source = new DOMSource(doc);
			transformer.transform(source, result);

			return result.getWriter().toString();
			
		} catch (Exception e) {
		}
		
		return null;
	}
}

package nng.christmas;

import nng.graph.Node;
import nng.graph.models.Cone;
import nng.graph.models.Cylinder;

public class TreeModel extends Node {
	
	private class TreeCone extends Cone {
		public TreeCone() {
			super();
			
			setFillColor(0.0, 0.25, 0.0, 1.0);
			setLineColor(0.0, 0.5, 0.0, 1.0);
			setRotation(-90.0, 0.0, 0.0);
			setScale(1.0,1.0,3.0);
		}
	}
	
	private class TreeTrunk extends Cylinder {
		public TreeTrunk(double height) {
			super();
			setFillColor(0.5, 0.25, 0.1, 1.0);
			setLineColor(1.0, 0.5, 0.2, 1.0);
			setRotation(-90.0, 0.0, 0.0);
			setScale(0.25,0.25,height);
		}
	}
	
	public TreeModel() {
		super();
		
		TreeCone[] cones = new TreeCone[3];
		
		double j;
		for (int i = 0; i < cones.length; i++) {
			cones[i] = new TreeCone();
			cones[i].setTranslation(0.0, i * 1.5, 0.0);
			
			j = (cones.length - i) + 1.0;
			cones[i].setScale(j,j,3.0);
			addNode(cones[i]);
		}

		TreeTrunk trunk = new TreeTrunk((cones.length * cones.length * 1.5) / 2.0);
		
		trunk.setTranslation(0.0, -(cones.length * 1.5 / 10.0) - 1.0, 0.0);
		addNode(trunk);
	}
}

/*
 * Copyright 2012 Alex Usachev, thothbot@gmail.com
 * 
 * This file is part of Parallax project.
 * 
 * Parallax is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by the 
 * Free Software Foundation, either version 3 of the License, or (at your 
 * option) any later version.
 * 
 * Parallax is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details.
 * 
 * You should have received a copy of the GNU General Public License along with 
 * Squirrel. If not, see http://www.gnu.org/licenses/.
 */

package thothbot.parallax.loader.shared.dae;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import thothbot.parallax.core.shared.Log;
import thothbot.parallax.core.shared.core.Matrix4f;

import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

public class DaeSkin extends DaeElement 
{
	private String source;
	
	private Map<String, DaeSource> sources;
	
	private Matrix4f bindShapeMatrix;
//	this.invBindMatrices = [];
//	this.joints = [];
//	this.weights = [];
	
	public DaeSkin(Node node) 
	{
		super(node);

		Log.debug("DaeSkin() " + toString());
	}

	@Override
	public void read() 
	{
		source = readAttribute("source", true);
		sources = new HashMap<String, DaeSource>();

		NodeList list = getNode().getChildNodes();
		for (int i = 0; i < list.getLength(); i++) 
		{
			Node child = list.item(i);
			String nodeName = child.getNodeName();

			if (nodeName.compareTo("source") == 0) 
			{
				DaeSource source = new DaeSource(child);
				sources.put(source.getID(), source);
			} 
			else if (nodeName.compareTo("bind_shape_matrix") == 0) 
			{
				DaeDummyElement bind_shape_matrix = new DaeDummyElement(child);
				float[] data = bind_shape_matrix.readFloatArray();
				bindShapeMatrix = new Matrix4f(
						data[0], data[1], data[2], data[3],
						data[4], data[5], data[6], data[7],
						data[8], data[9], data[10], data[11],
						data[12], data[13], data[14], data[15]
					);
			}
			else if (nodeName.compareTo("joints") == 0) 
			{
				readJoints(child);
			}
			else if (nodeName.compareTo("vertex_weights") == 0) 
			{
				reaWeights(child);
			}
		}
	}
	
	private void readJoints(Node node)
	{
		NodeList list = node.getChildNodes();
		List<DaeInput> input = new ArrayList<DaeInput>();
		
		for (int i = 0; i < list.getLength(); i++) 
		{
			Node child = list.item(i);
			String nodeName = child.getNodeName();

			if (nodeName.compareTo("input") == 0) 
			{
				input.add(new DaeInput(child));
			}
		}
	}
	
	private void reaWeights(Node node)
	{
		
	}
	
	public String toString()
	{
		return "source=" + this.source + ", bind_shape_matrix=" + this.bindShapeMatrix;
	}

}
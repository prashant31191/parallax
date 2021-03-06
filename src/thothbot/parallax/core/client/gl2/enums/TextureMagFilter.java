/*
 * Copyright 2012 Alex Usachev, thothbot@gmail.com
 * 
 * This file is part of Parallax project.
 * 
 * Parallax is free software: you can redistribute it and/or modify it 
 * under the terms of the Creative Commons Attribution 3.0 Unported License.
 * 
 * Parallax is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the Creative Commons Attribution 
 * 3.0 Unported License. for more details.
 * 
 * You should have received a copy of the the Creative Commons Attribution 
 * 3.0 Unported License along with Parallax. 
 * If not, see http://creativecommons.org/licenses/by/3.0/.
 */

package thothbot.parallax.core.client.gl2.enums;

import thothbot.parallax.core.client.gl2.WebGLConstants;

/**
 * The texture magnification function is used when the pixel being 
 * textured maps to an area less than or equal to one texture element.
 * 
 * @author thothbot
 *
 */
public enum TextureMagFilter implements GLEnum
{
	/**
	 * Returns the value of the texture element that is nearest 
	 * (in Manhattan distance) to the center of the pixel being textured.
	 */
	NEAREST(WebGLConstants.NEAREST),
	
	/**
	 * Returns the weighted average of the four texture elements that 
	 * are closest to the center of the pixel being textured.
	 */
	LINEAR(WebGLConstants.LINEAR);

	private final int value;

	private TextureMagFilter(int value) {
		this.value = value;
	}

	@Override
	public int getValue() {
		return value;
	}
}

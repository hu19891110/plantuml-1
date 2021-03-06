/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2017, Arnaud Roques
 *
 * Project Info:  http://plantuml.com
 * 
 * If you like this project or if you find it useful, you can support us at:
 * 
 * http://plantuml.com/patreon (only 1$ per month!)
 * http://plantuml.com/paypal
 * 
 * This file is part of PlantUML.
 *
 * PlantUML is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlantUML distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 *
 * Original Author:  Arnaud Roques
 *
 *
 */
package net.sourceforge.plantuml;

import net.sourceforge.plantuml.suggest.SuggestEngineResult;
import net.sourceforge.plantuml.suggest.SuggestEngineStatus;

public class ErrorUml {

	private final String error;
	private final ErrorUmlType type;
	private SuggestEngineResult suggest;
	private final LineLocation lineLocation;
//	private final int startLine;

	public ErrorUml(ErrorUmlType type, String error, LineLocation lineLocation) {
		if (error == null || type == null || StringUtils.isEmpty(error)) {
			throw new IllegalArgumentException();
		}
//		this.startLine = startLine;
		this.error = error;
		this.type = type;
		this.lineLocation = lineLocation;
	}

	@Override
	public boolean equals(Object obj) {
		final ErrorUml this2 = (ErrorUml) obj;
		return this.type == this2.type && this.getPosition() == this2.getPosition() && this.error.equals(this2.error);
	}

	@Override
	public int hashCode() {
		return error.hashCode() + type.hashCode() + getPosition() + (suggest == null ? 0 : suggest.hashCode());
	}

	@Override
	public String toString() {
		return type.toString() + " " + getPosition() + " " + error + " " + suggest;
	}

	public final String getError() {
		return error;
	}

	public final ErrorUmlType getType() {
		return type;
	}

	private int getPosition() {
		return lineLocation.getPosition();
	}

	public LineLocation getLineLocation() {
		return lineLocation;
	}

	public final SuggestEngineResult getSuggest() {
		return suggest;
	}

	public final boolean hasSuggest() {
		return suggest != null && suggest.getStatus() == SuggestEngineStatus.ONE_SUGGESTION;
	}

	public void setSuggest(SuggestEngineResult suggest) {
		this.suggest = suggest;
	}

}

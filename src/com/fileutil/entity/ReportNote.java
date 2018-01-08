package com.fileutil.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "ReportNote")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportNote {

	@XmlElement(name = "scope")
	private List<Scope> scopes = null;

	public List<Scope> getScope() {
		return scopes;
	}

	public void setScope(List<Scope> scope) {
		this.scopes = scope;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ReportNote>\n");

		for (Scope scope : scopes) {
			sb.append("\t<scope name='")
			.append(scope.getName())
			.append("'>\n\t\t<important>\n\t\t\t")
			.append(scope.getImportant())
			.append("\n\t\t<important>\n")
			.append("\t<scope>\n");
		}
		sb.append("<ReportNote>");

		return sb.toString();
	}
}

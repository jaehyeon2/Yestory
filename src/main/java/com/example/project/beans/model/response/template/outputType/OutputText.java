package com.example.project.beans.model.response.template.outputType;

import com.example.project.beans.model.response.template.Output;
import com.example.project.beans.model.response.template.output.simpleText.SimpleText;

public class OutputText extends Output{
	private SimpleText simpleText;

	public SimpleText getSimpleText() {
		return simpleText;
	}
	public void setSimpleText(SimpleText simpleText) {
		this.simpleText = simpleText;
	}
}

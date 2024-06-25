package com.example.project.beans.model.response.template.outputType;

import com.example.project.beans.model.response.template.Output;
import com.example.project.beans.model.response.template.output.basicCard.Carousel;

public class OutputBasicCard extends Output{

	private Carousel carousel;

	public Carousel getCarousel() {
		return carousel;
	}
	public void setCarousel(Carousel carousel) {
		this.carousel = carousel;
	}
}

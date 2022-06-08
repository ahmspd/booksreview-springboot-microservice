package com.lawencon.books.dto.review;

import java.util.List;

public class GetReviewsDtoRes {
	private List<GetReviewDtoData> data;
	
	public List<GetReviewDtoData> getData() {
		return data;
	}

	public void setData(List<GetReviewDtoData> data) {
		this.data = data;
	}
	
}

package com.lawencon.book.dto.genre;

import javax.validation.constraints.NotNull;

public class UpdateGenreDtoReq {
	@NotNull(message = "Id can't empy")
	private String id;
	
	@NotNull(message = "Genre Name can't empty")
	private String genreName;
	
	private Boolean isActive;

	@NotNull(message = "Updated By can't empty")
	private String updatedBy;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
}
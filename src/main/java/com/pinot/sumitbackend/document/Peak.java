/**
 * 
 */
package com.pinot.sumitbackend.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.mongodb.lang.Nullable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Document(collection = "peak")
public class Peak {
	
	@Id
	@Field("_id")
	@Getter
	private String id;
	
	@NotBlank
	@Size(max=10)
	@Getter
	@Setter
	@Field(name = "ele")
	private Integer elevation;
	
	@NotBlank
	@Size(max = 1000)
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	@Nullable
	private String wikipediaUri;
	
	@Getter
	@Setter
	@Field(name = "geometry")
	private GeoJsonPoint geometry;
	
	@Getter
	@Setter
	@Field(name="photoUrl")
	private String photoUrl;

	public Peak(@NotBlank @Size(max = 10) Integer elevation, @NotBlank @Size(max = 1000) String name,
			String wikipediaUri, GeoJsonPoint geometry, String photoUrl) {
		super();
		this.elevation = elevation;
		this.name = name;
		this.wikipediaUri = wikipediaUri;
		this.geometry = geometry;
		this.photoUrl = photoUrl;
	}
	
	
	
}

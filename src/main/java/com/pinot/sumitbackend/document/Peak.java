/**
 * 
 */
package com.pinot.sumitbackend.document;

import java.util.List;

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
 * Represents a peak entity stored in the MongoDB collection "peak".
 * This class is mapped to a MongoDB document and contains various attributes related to a peak.
 * 
 * <p><strong>Annotations used:</strong></p>
 * <ul>
 *   <li>{@code @Document(collection = "peak")} : Maps this class to the "peak" collection in MongoDB.</li>
 *   <li>{@code @Id} : Marks the primary key field.</li>
 *   <li>{@code @Field} : Specifies the MongoDB field name mapping.</li>
 *   <li>{@code @NotBlank} : Ensures that the field is not blank.</li>
 *   <li>{@code @Size} : Specifies constraints on the field length.</li>
 *   <li>{@code @Nullable} : Indicates that the field may be null.</li>
 *   <li>Lombok annotations ({@code @Getter}, {@code @Setter}) for automatic getter and setter generation.</li>
 * </ul>
 * 
 * <p><strong>Fields:</strong></p>
 * <ul>
 *   <li>{@code id} : Unique identifier for the peak.</li>
 *   <li>{@code elevation} : Elevation of the peak (in meters).</li>
 *   <li>{@code name} : Name of the peak.</li>
 *   <li>{@code wikipediaUri} : Optional Wikipedia link related to the peak.</li>
 *   <li>{@code geometry} : Geographical coordinates of the peak using {@link GeoJsonPoint}.</li>
 *   <li>{@code photoUrl} : URL of a photo representing the peak.</li>
 *   <li>{@code photoUrl} : URL of a photo representing the peak.</li>
 * </ul>
 * 
 * @author Anthony PINOT
 * @version 1.0
 */
@Document(collection = "peak")
public class Peak {

    /** Unique identifier for the peak. */
    @Id
    @Field("_id")
    @Getter
    private String id;

    /** Elevation of the peak (in meters). */
    @NotBlank
    @Size(max = 10)
    @Getter
    @Setter
    @Field(name = "ele")
    private Integer elevation;

    /** Name of the peak. */
    @NotBlank
    @Size(max = 1000)
    @Getter
    @Setter
    private String name;

    /** Optional Wikipedia link about the peak. */
    @Getter
    @Setter
    @Nullable
    private String wikipediaUri;

    /** Geographical location of the peak. */
    @Getter
    @Setter
    @Field(name = "geometry")
    private GeoJsonPoint geometry;

    /** URL of an image representing the peak. */
    @Getter
    @Setter
    @Field(name = "photoUrl")
    private String photoUrl;
    
    /** List of hiking URL */
    @Getter
    @Setter
    @Field(name = "linksUrl")
    private List<String> linksUrl;

    /**
     * Constructs a new {@code Peak} instance with the specified details.
     * 
     * @param elevation The elevation of the peak.
     * @param name The name of the peak.
     * @param wikipediaUri The optional Wikipedia URI for the peak.
     * @param geometry The geographical coordinates of the peak.
     * @param photoUrl The URL of the peak's photo.
     */
    public Peak(@NotBlank @Size(max = 10) Integer elevation, @NotBlank @Size(max = 1000) String name,
                String wikipediaUri, GeoJsonPoint geometry, String photoUrl, List<String> linksUrl) {
        this.elevation = elevation;
        this.name = name;
        this.wikipediaUri = wikipediaUri;
        this.geometry = geometry;
        this.photoUrl = photoUrl;
        this.linksUrl = linksUrl;
    }
}

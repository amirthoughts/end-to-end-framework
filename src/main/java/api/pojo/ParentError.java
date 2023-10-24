package api.pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "error"
})

@Value
//@Data
//@Getter
//@Setter
@Builder
@Jacksonized
@Generated("jsonschema2pojo")
public class ParentError {

    @JsonProperty("error")
    InnerError error;

}
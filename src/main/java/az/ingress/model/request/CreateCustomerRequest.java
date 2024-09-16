package az.ingress.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateCustomerRequest {

    @NotBlank(message = "validation.not.blank-pin")
    @Size(min = 7, max = 7, message = "validation.size-pin")
    private String pin;

    @NotBlank(message = "validation.not.blank-full-name")
    private String fullName;

    @NotBlank(message = "validation.not.blank-phone-number")
    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "validation.format-phone")
    private String phoneNumber;
}
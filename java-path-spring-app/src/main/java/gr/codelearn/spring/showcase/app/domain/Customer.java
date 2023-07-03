package gr.codelearn.spring.showcase.app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers", indexes = {@Index(columnList = "email", unique = true)})
@SequenceGenerator(name = "idGenerator", sequenceName = "customers_seq", initialValue = 1, allocationSize = 1)
public class Customer extends BaseModel {
	@NotNull(message = "Email address cannot be null")
	@Email
	@Column(length = 50, nullable = false)
	private String email;

	@NotNull()
	@Column(length = 20, nullable = false)
	private String firstname;

	@NotNull()
	@Column(length = 30, nullable = false)
	private String lastname;

	@Min(18)
	@Max(110)
	private Integer age;

	@Column(length = 50)
	@Size(max = 50, message = "Address cannot be bigger than 50 characters.")
	private String address;

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private CustomerCategory customerCategory;
}

package com.app.control.api.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "date", columnDefinition = "CURRENT_TIMESTAMP")
	@ApiModelProperty("Data/Hora do pedido")
	@NotNull(message = "Data do pedido não informada")
	private LocalDateTime dateTime;

	@Column(columnDefinition = "char(2) default 'PE'")
	@ApiModelProperty("Status do pedido: PE-Pendente, CA-Cancelado, AP-Aprovado, RE-Recusado")
	@NotEmpty(message = "Status do pedido não selecionado")
	private String status;
	
	@ApiModelProperty("Observação do pedido")
	private String observation;
	
	@Column(columnDefinition = "double(3,1) default 0.00")
	@ApiModelProperty("Valor da taxa de entrega do pedido")
	@NotNull(message = "Valor da taxa de entegrada deve ser informa")
	private Double delivery;
	
	@Column(nullable = false, columnDefinition = "double(3,1) default 0.00")
	@ApiModelProperty("Valor total do pedido")
	@NotEmpty(message = "Total do pedido deve ser informado")
	private Double total;

	@ManyToOne
	@JoinColumn(name = "payment_id")
	private Payment payment;

	@ManyToOne
	@JoinColumn(name = "contact_id")
	private Contact contact;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	@OneToMany(mappedBy = "order")
	private List<OrderItem> itens;

}

package nju.ktv.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="item_order_detail")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class Item_order_detail {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;//详细订单信息ID
	@NonNull
	private Integer orderId;//订单ID
	@NonNull
	private Integer itemId;//商品ID
	@NonNull
	private String itemName;//所购商品名称
	@NonNull
	private Integer num;//所购商品数量
	@NonNull
	private Double price;//商品单价
}

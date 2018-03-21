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
import nju.ktv.vo.Item_order_detail.Item_order_detailBuilder;

@Entity
@Table(name="item")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;//商品ID
	@NonNull
	private String name;//商品名称
	@NonNull
	private Integer num;//库存数量
	@NonNull
	private Double price;//单价
	@NonNull
	private Integer warnning_num;//警戒值
}

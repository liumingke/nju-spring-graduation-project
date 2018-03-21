package nju.ktv.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
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
@Table(name="item_order")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class Item_order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;//订单ID
	@NonNull
	private Double total;//总价
	@Column(columnDefinition="timestamp not null default now()" , updatable=false)
	private Timestamp ctime;
}

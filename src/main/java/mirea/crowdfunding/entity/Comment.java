package mirea.crowdfunding.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner_id")
	private User owner;
    @Column(name = "owner_id", updatable=false, insertable=false)
    private Integer ownerId;

	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "fundraising_id")
	private Fundraising fundraising;
    @Column(name = "fundraising_id", updatable=false, insertable=false)
    private Integer fundraisingId;

	@Column(columnDefinition = "TEXT")
	private String text;
}

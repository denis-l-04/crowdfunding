package mirea.crowdfunding.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Fundraising {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Setter
	@Column(nullable = false)
	private String name;

	@Setter
	@Column(nullable = false)
	private long collectedMoney;

	@Setter
	@Column(nullable = false)
	private long targetMoney;

	@Setter
	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "owner_id", updatable=false, insertable=false)
    private Integer ownerId;

	@Setter
    @ManyToOne()
	private Category category;

	@JsonIgnore
	@OneToMany(mappedBy = "fundraising")
	private Set<Comment> comments;
}

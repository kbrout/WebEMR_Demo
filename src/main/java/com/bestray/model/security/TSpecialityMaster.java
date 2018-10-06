package com.bestray.model.security;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="t_speciality_master")
public class TSpecialityMaster implements Serializable{
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Id
	    //@GeneratedValue
	    @Column(name = "ID")
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	     private Long id;
	    
	    /** The speciality name. */
	    @Column(name="speciality_name")
	    @NotNull
	    @Size(min = 4, max = 50)
	     private String specialityName;
	    
	    /** The speciality description. */
	    @Column(name="speciality_description")
	    @NotNull
	    @Size(min = 4, max = 100)
	    private String specialityDescription;
	    
	    /** The created date. */
	    @Column(name="created_date")
	    @NotNull
	    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
	     private Date createdDate;
	    
	    /** The updated date. */
	    @Column(name="updated_date")
	    @NotNull
	    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
	     private Date updatedDate;
	    
	    /** The record version. */
	    @Version
	    @Column(name = "record_version")
	    private Long recordVersion;
	    
	   /* @OneToMany(mappedBy="specialityId")
	    private Set<TDepartmentMaster> departmentSpeciality;*/
	    
	    /**
	     * Instantiates a new t speciality master.
	     */
	    public TSpecialityMaster() {
	    }
	    
	    public TSpecialityMaster(long specialityId, String specialityName, Date createdDate, Date updatedDate) {
	        this.specialityName = specialityName;
	        this.createdDate = createdDate;
	        this.updatedDate = updatedDate;
	     }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getSpecialityName() {
			return specialityName;
		}

		public void setSpecialityName(String specialityName) {
			this.specialityName = specialityName;
		}

		public String getSpecialityDescription() {
			return specialityDescription;
		}

		public void setSpecialityDescription(String specialityDescription) {
			this.specialityDescription = specialityDescription;
		}

		public Date getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}

		public Date getUpdatedDate() {
			return updatedDate;
		}

		public void setUpdatedDate(Date updatedDate) {
			this.updatedDate = updatedDate;
		}

		public Long getRecordVersion() {
			return recordVersion;
		}

		public void setRecordVersion(Long recordVersion) {
			this.recordVersion = recordVersion;
		}
	    
	    
}

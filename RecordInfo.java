package com.codigo.aplios.group.database.models.company;

import static com.codigo.aplios.group.database.DateTimeFormats.ISO_TIMESTAMP;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

enum AcceptedStates {
	A,
	O
}

enum RecordStates {
	A,
	O
}

@Embeddable
public final class RecordInfo {

	@Column(name = "AcceptedState")
	private AcceptedStates acceptedState;

	private RecordStates recordState;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "CreatedTimestamp")
	private Date createdTimestamp;

	@Column(name = "CreatedUser")
	private String createdUser;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "AcceptedTimestamp")
	private Date acceptedTimestamp;

	@Column(name = "AcceptedUser")
	private String acceptedUser;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "CanceledTimestamp")
	private Date canceledTimestamp;

	@Column(name = "CanceledUser")
	private String canceledUser;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "ModifiedTimestamp")
	private Date modifiedTimestamp;

	@Column(name = "ModifiedUser")
	private String modifiedUser;

	public AcceptedStates getAcceptedState() {

		return acceptedState;
	}

	public void setAcceptedState(final AcceptedStates acceptedState) {

		this.acceptedState = acceptedState;
	}

	public RecordStates getRecordState() {

		return recordState;
	}

	public void setRecordState(final RecordStates recordState) {

		this.recordState = recordState;
	}

	public Date getCreatedTimestamp() {

		return createdTimestamp;
	}

	public void setCreatedTimestamp(final Date createdTimestamp) {

		this.createdTimestamp = createdTimestamp;
	}

	public String getCreatedUser() {

		return createdUser;
	}

	public void setCreatedUser(final String createdUser) {

		this.createdUser = createdUser;
	}

	public Date getAcceptedTimestamp() {

		return acceptedTimestamp;
	}

	public void setAcceptedTimestamp(final Date acceptedTimestamp) {

		this.acceptedTimestamp = acceptedTimestamp;
	}

	public String getAcceptedUser() {

		return acceptedUser;
	}

	public void setAcceptedUser(final String acceptedUser) {

		this.acceptedUser = acceptedUser;
	}

	public Date getCanceledTimestamp() {

		return canceledTimestamp;
	}

	public void setCanceledTimestamp(final Date canceledTimestamp) {

		this.canceledTimestamp = canceledTimestamp;
	}

	public String getCanceledUser() {

		return canceledUser;
	}

	public void setCanceledUser(final String canceledUser) {

		this.canceledUser = canceledUser;
	}

	public Date getModifiedTimestamp() {

		return modifiedTimestamp;
	}

	@Override
	public int hashCode() {

		return Objects
			.hash(acceptedState, acceptedTimestamp, acceptedUser, canceledTimestamp, canceledUser, createdTimestamp, createdUser, modifiedTimestamp, modifiedUser, recordState);
	}

	@Override
	public boolean equals(final Object obj) {

		if (this == obj)
			return true;

		if (!(obj instanceof RecordInfo))
			return false;

		final RecordInfo other = (RecordInfo) obj;

		return (acceptedState == other.acceptedState) && Objects.equals(acceptedTimestamp, other.acceptedTimestamp)
				&& Objects.equals(acceptedUser, other.acceptedUser)
				&& Objects.equals(canceledTimestamp, other.canceledTimestamp)
				&& Objects.equals(canceledUser, other.canceledUser)
				&& Objects.equals(createdTimestamp, other.createdTimestamp)
				&& Objects.equals(createdUser, other.createdUser)
				&& Objects.equals(modifiedTimestamp, other.modifiedTimestamp)
				&& Objects.equals(modifiedUser, other.modifiedUser)
				&& (recordState == other.recordState);
	}

	@Override
	public String toString() {

		return "RecordInfo [acceptedState="
				+ acceptedState
				+ ", recordState="
				+ recordState
				+ ", createdTimestamp="
				+ (createdTimestamp != null
						? ISO_TIMESTAMP.format(createdTimestamp)
						: createdTimestamp)
				+ ", createdUser="
				+ createdUser
				+ ", acceptedTimestamp="
				+ (acceptedTimestamp != null
						? ISO_TIMESTAMP.format(acceptedTimestamp)
						: acceptedTimestamp)
				+ ", acceptedUser="
				+ acceptedUser
				+ ", canceledTimestamp="
				+ (canceledTimestamp != null
						? ISO_TIMESTAMP.format(canceledTimestamp)
						: canceledTimestamp)
				+ ", canceledUser="
				+ canceledUser
				+ ", modifiedTimestamp="
				+ (modifiedTimestamp != null
						? ISO_TIMESTAMP.format(modifiedTimestamp)
						: modifiedTimestamp)
				+ ", modifiedUser="
				+ modifiedUser
				+ "]";
	}

	public void setModifiedTimestamp(final Date modifiedTimestamp) {

		this.modifiedTimestamp = modifiedTimestamp;
	}

	public String getModifiedUser() {

		return modifiedUser;
	}

	public void setModifiedUser(final String modifiedUser) {

		this.modifiedUser = modifiedUser;
	}
}

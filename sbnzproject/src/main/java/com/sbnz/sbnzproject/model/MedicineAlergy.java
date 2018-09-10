package com.sbnz.sbnzproject.model;

public class MedicineAlergy {
	private Medicine medicine;
	private boolean alergic;
	
	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine m) {
		this.medicine = m;
	}

	public MedicineAlergy() {
		
	}
	public MedicineAlergy(Medicine m) {
		super();
		this.medicine = m;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((medicine == null) ? 0 : medicine.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicineAlergy other = (MedicineAlergy) obj;
		if (medicine == null) {
			if (other.getMedicine() != null)
				return false;
		} else if (!medicine.equals(other.getMedicine()))
			return false;
		return true;
	}

	public boolean isAlergic() {
		return alergic;
	}

	public void setAlergic(boolean alergic) {
		this.alergic = alergic;
	}
	
}

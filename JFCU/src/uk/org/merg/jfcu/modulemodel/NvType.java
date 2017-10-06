package uk.org.merg.jfcu.modulemodel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import uk.org.merg.jfcu.layoutmodel.Module;

@XmlRootElement
public class NvType {
	private String ui;
	@XmlElementRef(name="nvchoice", required=false)
	private NvChoice nvchoice;
	@XmlElementRef(name="nvinteger", required=false)
	private NvInteger nvinteger;
	private String nvCellEditor;
	
	
	public String getUi() {
		return ui;
	}
	@XmlElement
	public void setUi(String ui) {
		this.ui = ui;
	}
	
	
	public NvChoice getNvChoice() {
		return nvchoice;
	}
	@XmlElement(name="nvchoice")
	public void setNvChoice(NvChoice choice) {
		this.nvchoice = choice;
	}
	
	
	public NvInteger getNvInteger() {
		return nvinteger;
	}
	@XmlElement(name="nvinteger")
	public void setNvInteger(NvInteger integer) {
		this.nvinteger = integer;
	}
	
	
	public String getNvCellEditor() {
		return nvCellEditor;
	}
	@XmlElement(name="nvcelleditor")
	public void setNvCellEditor(String nvCellEditor) {
		this.nvCellEditor = nvCellEditor;
	}
	
	public String getValue(Module module, int id, int bitmask) {
		if (module.getNvs() == null) return "NO NVs";
		Byte nvVal = module.getNvs().get(id);
		if (nvVal == null) return "No NV";
		byte b = (byte) (nvVal & bitmask);
		if (nvinteger != null) {
			return ""+b;
		}
		if (nvchoice != null) {
			for (NvItem nvi : nvchoice.getNvitems()) {
				if (nvi.getValue() == b) return nvi.getSetting();
			}
			return "Invalid";
		}
		return "Unknown";
	}

}

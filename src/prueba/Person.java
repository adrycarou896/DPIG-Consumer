package prueba;
import java.io.Serializable;

import org.json.JSONObject;

public class Person implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7915536599168677189L;
	
	private String identificador;
	
	public Person() {}
	
	public Person(String identificador) {
		this.identificador = identificador;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	
	public JSONObject getJson() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("identificador", this.identificador);
		return jsonObject;
	}

}


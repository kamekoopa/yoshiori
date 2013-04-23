package models;

import java.security.SecureRandom;
import java.util.Random;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;

public abstract class Message {

	public static Message createMessage(String message){
		if (message.contains("love")){
			return new Message(){

				@Override protected String getLang() {
					return "en";
				}

				@Override protected String getMessage() {
					return "I love you";
				}

			};

		}else if(message.matches(".*?((好|す)き|愛してる)[\\?？]?")){
			return new Message(){

				private final String[] messages = new String[]{
					"好きだよ。",
					"愛してる。",
				};

				private final Random r = new SecureRandom();

				@Override protected String getLang() {
					return "ja";
				}

				@Override protected String getMessage() {

					return this.messages[this.r.nextInt(this.messages.length)];
				}
			};
		}else{
			return new Message(){

				@Override public int status(){
					return 400;
				}

				@Override protected String getLang() {
					return "ja";
				}

				@Override protected String getMessage() {
					return "ん？";
				}
			};
		}
	}

	public int status(){
		return 200;
	}

	protected abstract String getLang();
	protected abstract String getMessage();

	public JsonNode toJson() {

		ObjectNode object = JsonNodeFactory.instance.objectNode();
		object.put("lang", this.getLang());
		object.put("message", this.getMessage());

		return object;
	}
}
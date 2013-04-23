package controllers;

import models.Message;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	public static Result index(final String q) {

		Message m = Message.createMessage(q);

		return status(m.status(), m.toJson());
	}
}

import static play.mvc.Results.status;
import models.Message;
import play.GlobalSettings;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;


public class Global extends GlobalSettings {

	@Override
	public Result onError(RequestHeader request, Throwable t) {
		Message m = new Message() {

			@Override public int status(){
				return 500;
			}

			@Override protected String getMessage() {
				return "(あなたの質問に彼は混乱をきたし、破滅してしまったようです)";
			}

			@Override protected String getLang() {
				return "ja";
			}
		};

		return status(m.status(), m.toJson());
	}

	@Override
	public Result onHandlerNotFound(RequestHeader request) {

		Message m = new Message() {

			@Override public int status(){
				return 404;
			}

			@Override protected String getMessage() {
				return "(そこに彼は居ないようです)";
			}

			@Override protected String getLang() {
				return "ja";
			}
		};

		return status(m.status(), m.toJson());
	}

	@Override
	public Result onBadRequest(RequestHeader request, String error) {

		Message m = new Message() {

			@Override public int status(){
				return 400;
			}

			@Override protected String getMessage() {
				return "なんか言った？";
			}

			@Override protected String getLang() {
				return "ja";
			}
		};

		return status(m.status(), m.toJson());
	}
}

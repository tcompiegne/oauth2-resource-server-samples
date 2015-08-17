package actions;

import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Http.Context;
import play.mvc.Result;

public class CORSAction extends Action.Simple {

	@Override
	public Promise<Result> call(Context ctx) throws Throwable {
		Promise<Result> result = this.delegate.call(ctx);
		Http.Response response = ctx.response();
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Authorization, Content-Type");
		response.setHeader("Access-Control-Max-Age", "3600");
		return result;
	}

}

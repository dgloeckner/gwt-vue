package dg.gwtvue.web.jsinterop;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative=true, namespace= JsPackage.GLOBAL, name="window")
public class JsWindowWrapper {
  public static MiddlewareWrapper Middleware;
}

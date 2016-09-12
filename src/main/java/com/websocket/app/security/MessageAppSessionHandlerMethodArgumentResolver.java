package com.websocket.app.security;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.websocket.app.aspect.CurrentSession;
import com.websocket.app.aspect.Log;
import com.websocket.app.model.Session;

@Component
public class MessageAppSessionHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	private static @Log Logger logger;
	
	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		return methodParameter.getParameterAnnotation(CurrentSession.class) != null
				&& (methodParameter.getParameterType().equals(Session.class));
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		if (this.supportsParameter(parameter)) {
			Principal _principal = webRequest.getUserPrincipal();
			if (null == _principal) {
				HttpServletRequest _request = webRequest.getNativeRequest(HttpServletRequest.class);
				if (parameter.getParameterType().equals(Session.class)){
					Session session = new Session();
					/*String branches = _request.getParameter("clientBranches");
					String subClients = _request.getParameter("subClients");
					if(StringUtils.isBlank(branches))
						return null;
					if(StringUtils.isBlank(subClients))
						return null;
					session.setClientBranches(Util.convertCSVStringToList(branches));
					session.setSubClients(Util.convertCSVStringToList(subClients));
					session.setUserGroupId(Long.parseLong(_request.getParameter("userGroupId")));
					session.setClientId(Long.parseLong(_request.getParameter("clientId")));
					session.setToken(_request.getParameter("token"));
					session.setUserId(Long.parseLong(_request.getParameter("userId")));
					session.setSuperClientId(Long.parseLong(_request.getParameter("superClientId")));
					session.setModelType(_request.getParameter("modelType"));
					session.setTimeZone(_request.getParameter("timeZone"));
					session.setCurrentClientBranchId(Long.parseLong(_request.getParameter("currentClientBranchId")));*/
					return session;
				}
			} else if (((Authentication) _principal).getPrincipal() instanceof User) {
				Session _session = ((Session) ((Authentication) _principal).getCredentials());
				if (parameter.getParameterType().equals(Session.class)){
					return _session;
				}
			}
			return null;
		} else {
			return WebArgumentResolver.UNRESOLVED;
		}
	}
}
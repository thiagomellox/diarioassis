// ==============================================================================
// file : FacesUtils.java
// project: SISCOSERV
//
// last change: date: 2014-10-08
// by: Stefanini
// revision: 1.1
// ------------------------------------------------------------------------------
// copyright: Stefanini IT
// ==============================================================================
package util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;


/**
 * The Class FacesUtils.
 */
public final class FacesUtils {

    /**
     *  Default constructor
     */
    private FacesUtils() {

    }

    /**
     * Gets the servlet context.
     *
     * @return the servlet context
     */
    public static ServletContext getServletContext() {
        return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    }

    /**
     * Gets the managed bean.
     *
     * @param beanName the bean name
     * @return the managed bean
     */
    public static Object getManagedBean(final String beanName) {
        Object o = getValueBinding(getJsfEl(beanName)).getValue(FacesContext.getCurrentInstance());

        return o;
    }

    /**
     * Reset managed bean.
     *
     * @param beanName the bean name
     */
    public static void resetManagedBean(final String beanName) {
        getValueBinding(getJsfEl(beanName)).setValue(FacesContext.getCurrentInstance(), null);
    }

    /**
     * Set managed bean in session.
     *
     * @param beanName the bean name
     * @param managedBean the managed bean
     */
    @SuppressWarnings("unchecked")
    public static void setManagedBeanInSession(final String beanName, final Object managedBean) {
        getExternalContext().getSessionMap().put(beanName, managedBean);
    }

    /**
     * Remove o bean da aplicação
     * @param beanName nome do managed bean
     */
    @SuppressWarnings("unchecked")
    public static void removeManagedBeanFromSession(final String beanName){
        getExternalContext().getSessionMap().put(beanName, null);
//        FacesContext.getCurrentInstance().getApplication().createValueBinding(getJsfEl(beanName))
//            .setValue(FacesContext.getCurrentInstance(), null);
    }

    /**
     * Gets the request parameter.
     *
     * @param name the name
     * @return the request parameter
     */
    public static String getRequestParameter(final String name) {
        return (String) getExternalContext().getRequestParameterMap().get(name);
    }

    /**
     * Gets the response.
     *
     * @return the response
     */
    public static HttpServletResponse getResponse() {
        HttpServletResponse response = (HttpServletResponse) getExternalContext().getResponse();

        return response;
    }

    /**
     * Gets the message.
     *
     * @param key the key
     * @return the message
     */
    public static String getMessage(final String key) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String messageBundleName = facesContext.getApplication().getMessageBundle();
        Locale locale = facesContext.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle(messageBundleName, locale);

        return bundle.getString(key);
    }

    /**
     * Adiciona info message.
     *
     * @param msg the msg
     */
    public static void addInfoMessage(final String msg) {
        addInfoMessage(null, msg);
    }

    /**
     * Adiciona info message.
     *
     * @param msg the msg
     */
    public static void addInfoMessage(final FacesMessage msg) {
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Adiciona info message.
     *
     * @param clientId the client id
     * @param msg the msg
     */
    public static void addInfoMessage(final String clientId, final String msg) {
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
    }

    /**
     * Adiciona error message.
     *
     * @param msg the msg
     */
    public static void addErrorMessage(final String msg) {
        addErrorMessage(null, msg);
    }

    /**
     * Adiciona error message.
     *
     * @param msg the msg
     */
    public static void addErrorMessage(final FacesMessage msg) {
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Adiciona error message.
     *
     * @param clientId the client id
     * @param msg the msg
     */
    public static void addErrorMessage(final String clientId, final String msg) {
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
    }

    /**
     * Gets the application.
     *
     * @return the application
     */
    public static Application getApplication() {
        ApplicationFactory appFactory = (ApplicationFactory) FactoryFinder
            .getFactory(FactoryFinder.APPLICATION_FACTORY);

        return appFactory.getApplication();
    }

    /**
     * Gets the value binding.
     *
     * @param el the el
     * @return the value binding
     */
    public static ValueBinding getValueBinding(final String el) {
        return getApplication().createValueBinding(el);
    }

    /**
     * Gets the servlet request.
     *
     * @return the servlet request
     */
    public static HttpServletRequest getServletRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }

    /**
     * Gets the request attribute.
     *
     * @param <T> the generic type
     * @param classobject the classobject
     * @param attributeName the attribute name
     * @return the request attribute
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> T getRequestAttribute(final Class<T> classobject, final String attributeName) {
        Map attrMap = getExternalContext().getRequestMap();

        return (T) attrMap.get(attributeName);
    }

    /**
     * Set request attribute.
     *
     * @param attributeName the attribute name
     * @param object the object
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void setRequestAttribute(final String attributeName, final Object object) {
        Map attrMap = getExternalContext().getRequestMap();

        attrMap.put(attributeName, object);
    }

    /**
     * Removes request attribute.
     *
     * @param attributeName the attribute name
     */
    @SuppressWarnings({ "rawtypes" })
    public static void removeRequestAttribute(final String attributeName) {
        Map attrMap = getExternalContext().getRequestMap();

        attrMap.remove(attributeName);
    }

    /**
     * Gets the session attribute.
     *
     * @param <T> the generic type
     * @param classobject the classobject
     * @param attributeName the attribute name
     * @return the session attribute
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> T getSessionAttribute(final Class<T> classobject, final String attributeName) {
        Map sessionMap = getExternalContext().getSessionMap();

        return (T) sessionMap.get(attributeName);
    }

    /**
     * Set session attribute.
     *
     * @param attributeName the attribute name
     * @param object the object
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void setSessionAttribute(final String attributeName, final Object object) {
        Map sessionMap = getExternalContext().getSessionMap();

        sessionMap.put(attributeName, object);
    }

    /**
     * Removes session attribute.
     *
     * @param attributeName the attribute name
     */
    @SuppressWarnings({ "rawtypes" })
    public static void removeSessionAttribute(final String attributeName) {
        Map sessionMap = getExternalContext().getSessionMap();

        sessionMap.remove(attributeName);
    }

    /**
     * Gets the el value.
     *
     * @param el the el
     * @return the el value
     */
    public static Object getElValue(final String el) {
        return getValueBinding(el).getValue(FacesContext.getCurrentInstance());
    }

    /**
     * Gets the jsf el.
     *
     * @param value the value
     * @return the jsf el
     */
    public static String getJsfEl(final String value) {
        String stringValue = "#{";
        stringValue = stringValue + value + "}";
        return stringValue;
    }

    /**
     * Gets the current locale.
     *
     * @return the current locale
     */
    public static Locale getCurrentLocale() {
        return FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }

    /**
     * Gets the external context.
     *
     * @return the external context
     */
    public static ExternalContext getExternalContext() {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        if (facesContext != null) {
            return facesContext.getExternalContext();
        }
        else {
            return null;
        }
    }

    /**
     * Gets the context path.
     *
     * @return the context path
     */
    public static String getContextPath() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }

    /**
     * Gets the method binding.
     *
     * @param method the method
     * @return the method binding
     */
    public static MethodBinding getMethodBinding(final String method) {
        FacesContext context = FacesContext.getCurrentInstance();
        MethodBinding methodBinding = context.getApplication().createMethodBinding(method, null);

        return methodBinding;
    }

    /**
     * Gets the label.
     *
     * @param key the key
     * @return the label
     */
    public static String getLabel(final String key) {
        Object o = null;

        return getLabel(key, o);
    }

    /**
     * Gets the label.
     *
     * @param key the key
     * @param params the params
     * @return the label
     */
    public static String getLabel(final String key, final Object... params) {
        FacesContext context = FacesContext.getCurrentInstance();
        String text = null;

        try {
            ResourceBundle bundle = ResourceBundle.getBundle(context.getApplication().getMessageBundle());

            text = bundle.getString(key);
        }
        catch (final Exception e) {
            text = key;
        }

        if (params != null) {
            text = MessageFormat.format(text, params);
        }

        return text;
    }

    /**
     * Gets the resource bundle.
     *
     * @return the resource bundle
     */
    public static ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle());
    }

    /**
     * Ciclo ajax.
     *
     * @param event the event
     */
    public static void cicloAjax(final ValueChangeEvent event) {
        if (!event.getPhaseId().equals(PhaseId.INVOKE_APPLICATION)) {
            event.setPhaseId(PhaseId.INVOKE_APPLICATION);
            event.queue();

            return;
        }
    }
    
    public static void sucesso(){
    	RequestContext.getCurrentInstance().addCallbackParam("sucess", true);
    }
    
    public static void erro(){
    	RequestContext.getCurrentInstance().addCallbackParam("sucess", false);
    }


}

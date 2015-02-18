package org.cc.thot.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.cc.thot.dao.LocationDao;
import org.cc.thot.dao.SessionDao;
import org.cc.thot.dao.TimeslotDao;
import org.cc.thot.domain.Session;
import org.cc.thot.domain.SessionType;
import org.cc.thot.model.OpenSpaceFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/proposeNewSession")
public class ProposeSessionController {

    @Autowired
    private SessionDao sessionDao;
    
    @Autowired
    private TimeslotDao timeslotDao;
    
    @Autowired
    private LocationDao locationDao;
    
    public ProposeSessionController() {
	super();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView setupForm(ModelMap modelMap, HttpServletRequest request) {

	String day = request.getParameter("day");
	OpenSpaceFormData data = new OpenSpaceFormData();
	data.setDate(day);

	modelMap.put("sessionDataFormData", data);
	modelMap.put("day", day);

	return new ModelAndView("proposeNewSession", modelMap);
    }

    @RequestMapping(value = "/proposeNewSession", method = RequestMethod.POST)
    public ModelAndView processSubmit(HttpServletRequest request, ModelMap modelMap, @ModelAttribute("sessionDataFormData") OpenSpaceFormData formData, BindingResult result) {

	modelMap.put("sessionDataFormData", formData);

	if (result.hasErrors()) {
	    System.out.println("Validation errors ocurred.");
	    return new ModelAndView("proposeNewSession");
	}

	String start = formData.getStart();
	String end = formData.getEnd();
	Session session = new Session(formData.getDate(), start, formData.getLocation(), formData.getTitle(), formData.getSpeaker(), formData.getDescription());
	session.setEnd(end);
	session.setType(SessionType.openspaceanywhere);
	sessionDao.saveSession(session);

	return new ModelAndView("redirect:allSessions");
    }

}

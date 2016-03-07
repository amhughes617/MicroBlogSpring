package com.theironyard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by alexanderhughes on 3/7/16.
 */
@Controller
public class MicroBlogController {
    HashMap<String, User> users = new HashMap<String, User>();
    ArrayList<Message> messages = new ArrayList<Message>();

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        User user = users.get(session.getAttribute("userName"));
        if (user != null) {
            for (Message message : messages) {
                if (message.getAuthor().equals(user.getName())) {
                    message.setAuthor(true);
                }
                else message.setAuthor(false);
            }
            model.addAttribute("userName", session.getAttribute("userName"));

            model.addAttribute("messages", messages);
            return "home";
        }
        else {
            model.addAttribute("userName", session.getAttribute("userName"));
            return "home";
        }
    }
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName, String password) {
        session.setAttribute("userName", userName);
        User user = new User(userName, password);
        users.put(userName, user);
        return "redirect:/";
    }
    @RequestMapping(path = "/post-msg", method = RequestMethod.POST)
    public String post(String msg, HttpSession session) {
        User user = users.get(session.getAttribute("userName"));
        Message message = new Message(msg, user.getName(), messages.size());
        messages.add(message);
        return "redirect:/";
    }
    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public String delete(int id) {
        messages.remove(id);
        int i = 0;
        for (Message message : messages) {
            message.setId(i);
            i++;
        }
        return "redirect:/";
    }
    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}

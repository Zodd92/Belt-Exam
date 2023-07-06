package com.betaplan.kristian.beltexam.controllers;


import com.betaplan.kristian.beltexam.models.Baby;
import com.betaplan.kristian.beltexam.models.LoginUser;
import com.betaplan.kristian.beltexam.models.User;
import com.betaplan.kristian.beltexam.services.BabyService;
import com.betaplan.kristian.beltexam.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @Autowired
    UserService userService;
    @Autowired
    BabyService babyService;

    @GetMapping("/")
    public String index(Model model, @ModelAttribute("newUser") User newUser,
                        @ModelAttribute("newLogin") User newLogin, HttpSession session) {
        if(session.getAttribute("userId") != null) {
            return "redirect:/home";
        }


        // Bind empty User and LoginUser objects to the JSP
        // to capture the form input
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser,
                           BindingResult result, Model model, HttpSession session) {

        // Call a register method in the service
        // to do some extra validations and create a new user!
        User user = userService.register(newUser, result);


        if(result.hasErrors()) {
            // Be sure to send in the empty LoginUser before
            // re-rendering the page.
            model.addAttribute("newLogin", new LoginUser());
            return "index";
        }

        // No errors!
        // TO-DO Later: Store their ID from the DB in session,
        // in other words, log them in.
        session.setAttribute("userId", user.getId());

        return "redirect:/home";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
                        BindingResult result, Model model, HttpSession session) {

        // Add once service is implemented:
        User user = userService.login(newLogin, result);

        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index";
        }

        // No errors!
        // Store their ID from the DB in session,
        // in other words, log them in.
        session.setAttribute("userId", user.getId());

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model){
        if (session.getAttribute("userId")==null){
            return "redirect:/";
        }
        model.addAttribute("allBabies", babyService.getAllBabies());
        model.addAttribute("user", userService.findById((Long)session.getAttribute("userId")));
        return "home";
    }
    @GetMapping("/names/new")
    public String createNewName(@ModelAttribute("baby") Baby baby, Model model, HttpSession session) {
        if(session.getAttribute("userId") == null) {
            return "redirect:/home";
        }

        model.addAttribute("user", userService.findById((Long)session.getAttribute("userId")));
        return "new";
    }
    @PostMapping("/names/create")
    public String addName(@Valid @ModelAttribute("baby") Baby baby,BindingResult result, HttpSession session){
        if(session.getAttribute("userId")==null){
            return "redirect:/home";}
        if (result.hasErrors()){
            return "new";}

        User user = userService.findById((Long) session.getAttribute("userId"));

        babyService.saveBaby(baby);
        return "redirect:/home";
    }

    @GetMapping("/names/{babyId}")
    public String babyShow(@PathVariable("babyId")Long id, Model model,HttpSession session){
        Baby baby= babyService.findBabyById(id);

        model.addAttribute("userId",(Long) session.getAttribute("userId"));
        model.addAttribute("baby",baby);
        return "babydetails";
    }

    @GetMapping("/names/{babyId}/update")
    public String updateName( @PathVariable("babyId")Long id,Model model, HttpSession session) {
        if(session.getAttribute("userId") == null) {
            return "redirect:/";
        }

        Baby baby= babyService.findBabyById(id);
        model.addAttribute("baby",baby);

        return "edit";
    }
    @PutMapping("/names/{babyid}/edit")
    public String editName(@Valid @ModelAttribute("baby")Baby baby,
                           @PathVariable("babyid")Long id,
                           BindingResult result,
                           HttpSession session) {
        if (result.hasErrors()){
            return "edit";
        }
        baby.setUser(userService.findById((Long) session.getAttribute("babyid")));
        babyService.saveBaby(baby);
        return "redirect:/home";
    }

    @DeleteMapping("/delete/{babyId}")
    public String dele(@PathVariable("babyId")Long babyId, HttpSession session){
        Baby baby = babyService.findBabyById(babyId);
        babyService.delete(baby);
        return "redirect:/home";
    }


}

package com.eduonix.votingsystem.controller;

import org.jboss.logging.Logger;
import com.eduonix.votingsystem.entity.Candidate;
import com.eduonix.votingsystem.entity.Citizen;
import com.eduonix.votingsystem.repositories.CandidateRepo;
import com.eduonix.votingsystem.repositories.CitizenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class VotingController {

    public final Logger logger = Logger.getLogger(VotingController.class);

    @Autowired
    CitizenRepo citizenRepo;

    @Autowired
    CandidateRepo candidateRepo;

    @RequestMapping("/")
    public String goToVote() {
        logger.info("Returning vote.html file");
        return "vote.html";
    }

    @RequestMapping("/doLogin")
    public String doLogin(@RequestParam String name, Model model, HttpSession session) {

        logger.info("getting citizen form database");

        Citizen citizen = citizenRepo.findByName(name);

        logger.info("putting citizen into session");
        session.setAttribute("citizen", citizen);

        if(!citizen.getHasVoted()) {
            logger.info("putting candidate into model");
            List<Candidate> candidates = candidateRepo.findAll();
            model.addAttribute("candidates",candidates);

            return "/performVoted.html";
        } else {
            return "/alreadyVoted.html";
        }
    }

    @RequestMapping("voteFor")
    public String voteFor(@RequestParam Long id, HttpSession session) {
        logger.info("voting");
        Citizen citizen = (Citizen) session.getAttribute("citizen");


        if(!citizen.getHasVoted()) {

            citizen.setHasVoted(true);

            Candidate c = candidateRepo.findById(id);
            logger.info("voting for candidate "+c.getName());

            c.setNumberOfVotes(c.getNumberOfVotes() + 1);
            candidateRepo.save(c);
            citizenRepo.save(citizen);

            return "voted.html";
        }

        return "alreadyVoted.html";
    }

    @RequestMapping("/goLoginPage")
    public String goLoginPage() {
        return "login.html";
    }

    @RequestMapping("/doAdmin")
    public String doAdmin(@RequestParam String login, @RequestParam String password, Model model) {

        if("Tomek".equals(login) && "tom123".equals(password)) {

            List<Candidate> candidates = candidateRepo.findAll();
            model.addAttribute("candidates",candidates);
            return "statistics.html";
        } else {
            return "noPermission.html";
        }
    }



}

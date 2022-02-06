package org.example.controllers;

import org.example.handler.RepeatWordTracker;
import org.example.dao.WordListDao;
import org.example.model.WordList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;


@Controller
@RequestMapping("/finder")
public class WordTrackerController {

    private static final Logger logger = LoggerFactory.getLogger(WordListDao.class);

    public final WordListDao wld;

    public RepeatWordTracker rwt;


    @Autowired
    public WordTrackerController(WordListDao wld, RepeatWordTracker rwt){
        this.wld = wld; this.rwt = rwt;
    }

    @GetMapping()
    public String findWord(@ModelAttribute("wordList") WordList wordList) {
        return "words/startPage";
    }

    @PostMapping("/result")
    public String create(@ModelAttribute("wordList") @Valid WordList wordList,
                         BindingResult bindingResult) throws IOException{
        if (bindingResult.hasErrors()) {
            logger.info("INPUT ERROR");
            return "words/startPage";
        }

        String url = wordList.getUrl();
        wordList.setList(RepeatWordTracker.readFromWeb(url));
        wld.saveWordList(wordList);
        logger.info("OPERATION COMPLETED");

        return "words/resultPage";
    }
}

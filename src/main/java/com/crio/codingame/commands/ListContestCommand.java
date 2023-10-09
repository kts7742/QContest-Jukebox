package com.crio.codingame.commands;

import java.util.ArrayList;
import java.util.List;

import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.Level;
import com.crio.codingame.services.IContestService;

public class ListContestCommand implements ICommand{

    private final IContestService contestService;
    
    public ListContestCommand(IContestService contestService) {
        this.contestService = contestService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute getAllContestLevelWise method of IContestService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["LIST_CONTEST","HIGH"]
    // or
    // ["LIST_CONTEST"]

    @Override
    public void execute(List<String> tokens) {
        if(tokens.size()==1){
            List<Contest> list = contestService.getAllContestLevelWise(null);
            // for(Contest contest : list){
            //     System.out.println(contest);
            // }
            System.out.println(list);
            return;
        }
        String level = tokens.get(1);
        Level l = Level.LOW;
        List<Contest> list = new ArrayList<>();
        if(level.equals("LOW")){
            list = contestService.getAllContestLevelWise(l);
        }else if(level.equals("MEDIUM")){
            l = Level.MEDIUM;
            list = contestService.getAllContestLevelWise(l);
        }else{
            l = Level.HIGH;
            list = contestService.getAllContestLevelWise(l);
        }
        // for(Contest contest : list){
        //     System.out.println(contest);
        // }
        System.out.println(list);
    }
    
}

# Goodwill Reminder

## Function
Basic to do list application developed for the CPSC 210 Software Construction course project. Stores all accomplished, current and failed tasks, and offers negative feedback for failure. Implements state-persitence with JSON-files and uses a Jpanel GUI to view current tasks, input new tasks and transfer tasks to the failed or accomplished category.

## Who will use it

Anyone who lives a normal lifestyle and is concerned about the largely unseen repercussion of their day to day actions

## Why is this project of interest to me

I have several motivations:
- Help the **environment** and others in need
- People wan to help but prioritise their day to day
    - A *belief* that people want to help but are often too involved in their life to determine how to best assist those 
    in need

## User Stories

- As a user, I want to be able to create a new task and add it to a to-do list
- As a user, I want to be able to mark a task as complete and move it to the list of accomplishment
- As a user, I want to be able to mark a task as failed, and move it to the list of failures
- As a user, I want to be able to view the list of tasks on my to-do list
- As a user, I want to be able to save my to-do list to file
- As a user, I want to be able to be able to load my to-do list from file 

## Notes to Grader

## Phase 4: Task 2
- I implemented the robust exceptions option. 
- Class:Task 
- Methods:constructor and checkURL

## Phase 4: Task 3
- I would create an Accomplishment class that extends Task
- I would create a Failure class that extends Task
- I would have made an Abstract TaskManager class that a GuiTaskManager and AtonementAPPTaskManager extend

# zajavka_ogarnizer

Application for Streamlining the Management of a Company Offering Office Equipment Service

The application is designed to streamline the management of a company that provides office equipment service (computers, printers, copiers, scanners, etc.) to businesses. The project facilitates cooperation among service employees. It was developed based on personal experience working in a service where such an application was lacking.
</br>
</br>
The entire context of the application revolves around adding and managing tasks commissioned by clients, which will henceforth be referred to simply as tasks.
</br>

Tasks are divided into three categories:
 - Services performed at the client's location (away work)
 - Services conducted at the company's headquarters (service)
 - Orders (order)
 </br>

When adding a task, it is possible to specify its execution priority (low, medium, high).
</br>
</br>
The application also stores basic information about the company's clients. To facilitate the addition of tasks, a previously added client can be selected from a list in the task addition panel.
</br>
</br>
A task can be updated by entering additional text information and changing its current stage. The following stages are distinguished:
 - Just added  assigned by default to newly added tasks
 - In progress 
 - Waiting for parts 
 - Ready to invoice 
</br>
A task can be approved upon successful completion or deleted if abandoned. In both cases, the task will be moved to the list of closed tasks (closed away work, closed service, closed order) with appropriate information about the outcome (in the success column). Completely removing a task from the application occurs after it is deleted from the list of closed tasks.
</br>

Users are divided into two roles:
 - serviceman
 - admin
</br>

A serviceman has the following permissions:
 - View, add, update, and close tasks (either by approval or deletion).
 - View and add clients.
</br>

Besides the serviceman's permissions, the admin also has the ability to:
 - delete clients
 - view lists, add, and remove users
 - view lists, delete closed tasks (closed work, closed service, closed order)

</br>

The application also features a function to fetch random data separately for each type of tasks and clients from a dedicated API (zajavka-ogarnizerAPI). 

For the proper functioning of the entire application, it is necessary to log in, as it uses the data of the logged-in user.

Login details: 
 - login: Karol  password: karol123  [serviceman]
 - login: Wojtek password: wojtek123 [admin]






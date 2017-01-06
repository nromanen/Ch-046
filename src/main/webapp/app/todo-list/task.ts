/**
 * Created by Admin on 12.07.16.
 */
export class Task{
    title: string;
    completed: boolean;
    id: number;

    constructor(title: string, completed: boolean, id: number){
        this.title = title;
        this.completed = completed;
        this.id = id;
    }
}
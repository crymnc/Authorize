import {Role} from "./role";
import {ComponentContent} from "./component.content";
import {UserComponent} from "./user.component";

export class User {

  id: number;
  name: string;
  lastName: string;
  username: string;
  active: boolean;
  lastActivationDate: any;
  roles: Array<Role> = new Array<Role>();
  userComponentContents: Array<ComponentContent> = new Array<ComponentContent>();

  constructor() {
  }

  static deserialize(input: any): User {
    let user:User = new User();
    Object.assign(user , input);
    for(let key of Object.keys(input.componentContents)){
      let componentContent:ComponentContent = new ComponentContent();
      let component: UserComponent = new UserComponent();
      component.id = parseInt(key);
      componentContent.component = component;
      componentContent.content = input.componentContents[key];
      user.userComponentContents.push(componentContent);
    }
    return user;
  }

}

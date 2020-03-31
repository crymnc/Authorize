export class Constant{
  id:number;
  name:string;
  constructor(){}

  static deserialize(input: any): Constant {
    let constant:Constant = new Constant();
    Object.assign(constant , input);
    return constant;
  }
}

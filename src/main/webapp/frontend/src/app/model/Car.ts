export class Car {
  brand;
  models;
  constructor() {
    this.brand = "Skoda";
    this.models = [
      new Model("Superb", "105"),
      new Model("Octavia", "75"),
      new Model("Fabia", "90"),
      new Model("Kodaq", "120")];

  }

}

export class Model {
  name;
  hp;
  isSelected;

  constructor(name, hp) {
    this.name = name;
    this.hp = hp;
    this.isSelected = false;
  }
}

class GlobalVariable {
  static final GlobalVariable _instance = new GlobalVariable._internal();
  factory GlobalVariable() => _instance;

  String title = "SherEats";

  GlobalVariable._internal() {
  }

}


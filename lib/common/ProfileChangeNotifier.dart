import 'package:flutter/cupertino.dart';
import 'package:provider/provider.dart';

class ProfileChangeNotifier extends ChangeNotifier {
  // Profile get _profile => Global.profile;

  @override
  void notifyListeners() {
    // Global.saveProfile();
    super.notifyListeners();
  }
}
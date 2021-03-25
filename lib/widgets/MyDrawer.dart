import 'package:provider/provider.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:shereats/models/user.dart';
import 'package:cached_network_image/cached_network_image.dart';


class MyDrawer extends StatelessWidget{
  const MyDrawer({Key key,}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Drawer(
      child: MediaQuery.removePadding(
          context: context,
          removeTop: true,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              _buildHeader(),
              Expanded(child: _buildMenus()),
            ],
          ),
      ),
    );
  }


  Widget _buildHeader(){
    return Consumer<User>(
      builder: (BuildContext context, User value, Widget child){
        return GestureDetector(
          child: Container(
            color: Theme.of(context).primaryColor,
            padding: EdgeInsets.only(top: 40, bottom: 20),
            child: Row(
              children: <Widget>[
                Padding(
                    padding: const EdgeInsets.symmetric(horizontal: 16.0),
                    child: ClipOval(
                      child: Image.asset(
                        "images/avatar-default.png",
                        width: 80,
                      ),
                    ),
                ),
                Text("Test")
              ],
            ),
          ),
          onTap: (){

          },
        );
      },
    );
  }

  // 构建菜单项
  Widget _buildMenus() {
    return Consumer<User>(
      builder: (BuildContext context, User userModel, Widget child) {
        // var gm = GmLocalizations.of(context);
        return ListView(
          children: <Widget>[
            ListTile(
              leading: const Icon(Icons.color_lens),
              title: Text("gm.theme"),
              onTap: () => Navigator.pushNamed(context, "themes"),
            ),
            ListTile(
              leading: const Icon(Icons.language),
              title: Text("gm.language"),
              onTap: () => Navigator.pushNamed(context, "language"),
            ),
            if(true) ListTile(
              leading: const Icon(Icons.power_settings_new),
              title: Text("gm.logout"),
              onTap: () {
                showDialog(
                  context: context,
                  builder: (ctx) {
                    //退出账号前先弹二次确认窗
                    return AlertDialog(
                      content: Text("gm.logoutTip"),
                      actions: <Widget>[
                        FlatButton(
                          child: Text("gm.cancel"),
                          onPressed: () => Navigator.pop(context),
                        ),
                        FlatButton(
                          child: Text("gm.yes"),
                          onPressed: () {
                            //该赋值语句会触发MaterialApp rebuild
                            // userModel.user = null;
                            Navigator.pop(context);
                          },
                        ),
                      ],
                    );
                  },
                );
              },
            ),
          ],
        );
      },
    );
  }

  Widget gmAvatar(String url, {
    double width = 30,
    double height,
    BoxFit fit,
    BorderRadius borderRadius,
  }){
    var placeholder = Image.asset(
        "images/avatar-default.png", //头像占位图，加载过程中显示
        width: width,
        height: height
    );
    return ClipRRect(
      borderRadius: borderRadius ?? BorderRadius.circular(2),
      child: CachedNetworkImage(
        imageUrl: url,
        width: width,
        height: height,
        fit: fit,
        placeholder: (context, url) =>placeholder,
        errorWidget: (context, url, error) =>placeholder,
      ),
    );
  }
}
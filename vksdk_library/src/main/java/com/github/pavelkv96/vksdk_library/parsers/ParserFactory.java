package com.github.pavelkv96.vksdk_library.parsers;

import com.github.pavelkv96.vksdk_library.model.dialogs.IDialogsListParser;
import com.github.pavelkv96.vksdk_library.model.messages.IMessageListParser;
import com.github.pavelkv96.vksdk_library.model.users.IUsersListParser;
import com.github.pavelkv96.vksdk_library.parsers.dialogs.DialogJsonListInObjectParser;
import com.github.pavelkv96.vksdk_library.parsers.messages.MessagesJsonListInObjectParser;
import com.github.pavelkv96.vksdk_library.parsers.users.UsersJsonListInObjectParser;

public class ParserFactory {
    public IMessageListParser createParserJsonListInObject(String pSource){
        return new MessagesJsonListInObjectParser(pSource);
    }

    public IDialogsListParser createParserJsonListInObject1(String pSource){
        return new DialogJsonListInObjectParser(pSource);
    }

    public IUsersListParser createParserJsonListInObject2(String pSource){
        return new UsersJsonListInObjectParser(pSource);
    }
}

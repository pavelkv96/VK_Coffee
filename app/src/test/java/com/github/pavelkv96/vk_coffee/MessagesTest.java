package com.github.pavelkv96.vk_coffee;

import com.github.pavelkv96.vk_coffee.mocks.Mocks;
import com.github.pavelkv96.vksdk_library.model.dialogs.IDialogItem;
import com.github.pavelkv96.vksdk_library.model.dialogs.IDialogsList;
import com.github.pavelkv96.vksdk_library.model.messages.IVKApiMessagesList;
import com.github.pavelkv96.vksdk_library.model.messages.VKApiMessage;
import com.github.pavelkv96.vksdk_library.model.users.IUsers;
import com.github.pavelkv96.vksdk_library.model.users.IUsersList;
import com.github.pavelkv96.vksdk_library.parsers.ParserFactory;
import com.github.pavelkv96.vksdk_library.streams.HttpClient;
import com.github.pavelkv96.vksdk_library.streams.IHttpClient;
import com.github.pavelkv96.vksdk_library.utils.VkUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.InputStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MessagesTest {
    private IHttpClient mHttpClient;

    @Before
    public void setUp() {
        mHttpClient = mock(HttpClient.class);
    }

    @Test
    public void Test() throws Exception {
        IVKApiMessagesList messages = new ParserFactory().createParserJsonListInObject(getMessages()).parse();
        for (VKApiMessage a : messages.getListMessages()) {
            System.out.println("id              : " + a.id());
            System.out.println("body            : " + a.body());
            System.out.println("date            : " + a.date());
            System.out.println("user_id         : " + a.user_id());
            System.out.println("read_state      : " + a.read_state());
            System.out.println("out             : " + a.out());
            System.out.println("fwd_messages    : " + a.fwd_messages());
            System.out.println("chat_id         : " + a.chat_id());
            System.out.println("\n");
        }
    }

    @Test
    public void Test2() throws Exception {
        IDialogsList iDialogsList = new ParserFactory().createParserJsonListInObject1(getDialogs()).parse();
        for (IDialogItem iDialogItem : iDialogsList.getListDialogs()) {
            System.out.println(iDialogItem.unread());
            //System.out.println(iDialogItem.idialog().date());
            System.out.println(iDialogItem.idialog().photo_50());
            System.out.println(iDialogItem.in_read());
            System.out.println(iDialogItem.out_read() + "\n");
        }
    }

    @Test
    public void Test3() throws Exception {
        IUsersList iUsersList = new ParserFactory().createParserJsonListInObject2(getUsers()).parse();
        for (IUsers iUsers : iUsersList.getUsers()) {
            System.out.print("|" + iUsers.last_name());
            System.out.print("|" + iUsers.is_friend());
            System.out.print("|" + iUsers.online());
            System.out.print("|" + iUsers.last_seen().time());
            System.out.print("|" + iUsers.last_seen().platform() + "\n");
        }
    }

    private String getMessages() throws Exception {
        InputStream mockedInputStream = Mocks.stream("messages.json");
        when(mHttpClient.get(Matchers.anyString())).thenReturn(mockedInputStream);
        return VkUtils.convertStreamToString(mHttpClient.get(""));
    }

    private String getDialogs() throws Exception {
        InputStream mockedInputStream = Mocks.stream("dialogs.json");
        when(mHttpClient.get(Matchers.anyString())).thenReturn(mockedInputStream);
        return VkUtils.convertStreamToString(mHttpClient.get(""));
    }

    private String getUsers() throws Exception {
        InputStream mockedInputStream = Mocks.stream("users.json");
        when(mHttpClient.get(Matchers.anyString())).thenReturn(mockedInputStream);
        return VkUtils.convertStreamToString(mHttpClient.get(""));
    }
}

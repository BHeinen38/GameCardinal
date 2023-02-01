package com.example.gamecardinal;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


import com.example.gamecardinal.fragments.SettingsFragment;

import org.junit.Test;

public class SettingFragmentMockitoTest {
        @Test
        public void myTest() throws Exception {
            // 1. create mock
            SettingsFragment testerOne = mock(SettingsFragment.class, CALLS_REAL_METHODS);

            testerOne.recieveUserInformation("Legend27", "Elon Musk", "ElonMusk27@gmail.com", "1235678910", true);

            System.out.println("Returned username: "+testerOne.getUsersUsername());
            System.out.println("Returned name: "+testerOne.getUsersName());
            System.out.println("Returned email: "+testerOne.getUsersEmail());
            System.out.println("Returned phone#: "+testerOne.getUsersPhoneNumber());
            System.out.println("Returned darkmode val: "+testerOne.getUsersModePreference());


        }
}

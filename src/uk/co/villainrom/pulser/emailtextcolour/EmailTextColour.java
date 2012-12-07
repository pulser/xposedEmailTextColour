package uk.co.villainrom.pulser.emailtextcolour;

import android.content.res.XModuleResources;
import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;

public class EmailTextColour implements IXposedHookZygoteInit, IXposedHookInitPackageResources{
	private String modulePath;
	
	@Override
	public void handleInitPackageResources(InitPackageResourcesParam resparam)
			throws Throwable {
		try {
			if (resparam.packageName.equals("com.android.email"))
			{
				XModuleResources modRes = XModuleResources.createInstance(modulePath, resparam.res);
				
				resparam.res.setReplacement("com.android.email", "color", "message_list_read_text_sub_text_dark_color_dark_theme", modRes.fwd(R.color.message_list_read_text_sub_text_dark_color_dark_theme));
				resparam.res.setReplacement("com.android.email", "color", "message_list_read_text_main_text_dark_color_dark_theme", modRes.fwd(R.color.message_list_read_text_main_text_dark_color_dark_theme));
				resparam.res.setReplacement("com.android.email", "color", "message_list_read_text_date_dark_color_dark_theme", modRes.fwd(R.color.message_list_read_text_date_dark_color_dark_theme));
				resparam.res.setReplacement("com.android.email", "color", "message_list_read_text_snippet_dark_color_dark_theme", modRes.fwd(R.color.message_list_read_text_snippet_dark_color_dark_theme));
			}
				
		}
		catch (Throwable t) { XposedBridge.log(t); }
	}

	@Override
	public void initZygote(StartupParam startupParam) throws Throwable {
		modulePath = startupParam.modulePath;
	}

}

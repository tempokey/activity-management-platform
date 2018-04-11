package com.edudigital.cloudy.amp.util.base.util;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class ImageUtils {

	public final static String IMAGE_META = "meta";
	public final static String IMAGE_HEIGHT = "height";
	public final static String IMAGE_WIDTH = "width";
	private final static String IMAGE_KEY_HEIGHT = "Image Height";
	private final static String IMAGE_KEY_WIDTH = "Image Width";
	private final static String IMAGE_KEY_SPACE = " ";

	public static Map<String, String> getImageInfo(File file) {
		Map<String, String> map = new HashMap<>();
		Metadata metadata = null;
		try {
			metadata = ImageMetadataReader.readMetadata(file);
			Iterator<Directory> it = metadata.getDirectories().iterator();
			while (it.hasNext()) {
				Directory exif = it.next();
				Iterator<Tag> tags = exif.getTags().iterator();
				// 文件详细信息的保存
				map.put(IMAGE_META, exif.getTags().toString());
				while (tags.hasNext()) {
					Tag tag = (Tag) tags.next();
					if (tag.getTagName().equals(IMAGE_KEY_HEIGHT)) {
						String height = tag.getDescription();
						String desc1[] = height.split(IMAGE_KEY_SPACE);
						map.put(IMAGE_HEIGHT, desc1[0]);
					}
					if (tag.getTagName().equals(IMAGE_KEY_WIDTH)) {
						String width = tag.getDescription();
						String desc2[] = width.split(IMAGE_KEY_SPACE);
						map.put(IMAGE_WIDTH, desc2[0]);
					}

				}
			}
		} catch (Exception e) {
		}
		return map;
	}
}

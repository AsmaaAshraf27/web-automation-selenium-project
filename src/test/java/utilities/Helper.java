package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Helper {
    //method take screenshot when the test cases fail
    public static void captureScreenShot(WebDriver driver,String screenShotName)
    {

        Path destination= Paths.get("./screenshots",screenShotName+".png");
        try {
            Files.createDirectories(destination.getParent());
            FileOutputStream fileOutputStream=new FileOutputStream(destination.toString());
            fileOutputStream.write(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES));
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot"+e.getMessage());
        }


    }

}
/*
الكود ده عبارة عن كلاس في Java باسم Helper، وهو يحتوي على دالة (Method) تقوم بأخذ لقطة شاشة (Screenshot) عند فشل الاختبارات في Selenium WebDriver.
سأشرح لك كل سطر بالتفصيل 👇:

📌 1. تعريف الحزمة (Package)
java
Copy
Edit
package utilities;
package utilities;

الكود موجود في حزمة (Package) باسم utilities.

تساعد الحزم في تنظيم الكود وتقسيمه إلى وحدات صغيرة.

📌 2. استيراد المكتبات الضرورية
java
Copy
Edit
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
🔹 هذه المكتبات توفر الأدوات اللازمة لأخذ لقطة شاشة وحفظها:

TakesScreenshot → يتيح أخذ Screenshot باستخدام WebDriver.

OutputType → يحدد نوع البيانات عند التقاط الصورة (هنا تستخدم كـ Bytes).

WebDriver → يمثل المتصفح المستخدم.

FileOutputStream → يستخدم لحفظ الصورة في ملف.

IOException → لمعالجة الأخطاء أثناء الحفظ.

Files و Path و Paths → للتعامل مع الملفات والمسارات.

📌 3. إنشاء كلاس Helper
java
Copy
Edit
public class Helper {
public class Helper {

تعريف كلاس عام باسم Helper يحتوي على دالة مساعدة لأخذ لقطات الشاشة.

📌 4. تعريف دالة captureScreenShot
java
Copy
Edit
public static void captureScreenShot(WebDriver driver, String screenShotName)
public static → دالة عامة يمكن استدعاؤها مباشرة بدون إنشاء كائن من Helper.

void → لا تُرجع أي قيمة.

captureScreenShot → اسم الدالة.

(WebDriver driver, String screenShotName)

WebDriver driver → نمرر WebDriver المستخدم في الاختبار.

String screenShotName → اسم ملف الصورة التي سيتم حفظها.

📌 5. تحديد مكان حفظ الصورة
java
Copy
Edit
Path destination = Paths.get("./screenshots", screenShotName + "png");
Paths.get("./screenshots", screenShotName + "png")

يُنشئ مسار (Path) لحفظ لقطة الشاشة داخل مجلد screenshots في المشروع.

اسم الملف سيكون screenShotName.png (لكن في الكود الحالي هناك خطأ يجب تصحيحه، سأوضحه لاحقًا).

📌 6. إنشاء المجلد إذا لم يكن موجودًا
java
Copy
Edit
Files.createDirectories(destination.getParent());
createDirectories(destination.getParent())

يتحقق مما إذا كان المجلد ./screenshots موجودًا، وإذا لم يكن موجودًا، يتم إنشاؤه.

📌 7. أخذ لقطة الشاشة وتحويلها إلى بيانات بايت
java
Copy
Edit
FileOutputStream out = new FileOutputStream(destination.toString());
out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
out.close();
((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)

يحوّل driver إلى كائن TakesScreenshot حتى نتمكن من التقاط لقطة شاشة.

يقوم getScreenshotAs(OutputType.BYTES) بالتقاط الصورة وإرجاعها كـ Bytes.

FileOutputStream out = new FileOutputStream(destination.toString());

يفتح ملفًا جديدًا في destination لكتابة الصورة فيه.

out.write(...)

يكتب البيانات Bytes داخل الملف.

out.close();

يغلق FileOutputStream لتوفير الموارد.

📌 8. التعامل مع الأخطاء
java
Copy
Edit
} catch (IOException e) {
    System.out.println("Exception while taking screenshot" + e.getMessage());
}
إذا حدث خطأ أثناء الحفظ، يتم طباعة رسالة الخطأ في الكونسول.

❌ 🚨 خطأ في الكود
في السطر:

java
Copy
Edit
Path destination = Paths.get("./screenshots", screenShotName + "png");
هناك خطأ لأن "png" يجب أن يكون ".png" حتى يكون اسم الملف صحيحًا.

✅ التصحيح:

java
Copy
Edit
Path destination = Paths.get("./screenshots", screenShotName + ".png");
✅ طريقة استدعاء الدالة في اختبار Selenium
في أي Test Class، يمكنك استدعاء captureScreenShot عند فشل اختبار:

java
Copy
Edit
@Test
public void testExample() {
    WebDriver driver = new ChromeDriver();
    driver.get("https://example.com");

    try {
        // مثال لفشل الاختبار
        Assert.assertTrue(driver.getTitle().contains("Wrong Title"));
    } catch (AssertionError e) {
        Helper.captureScreenShot(driver, "testFailure");
        System.out.println("Screenshot captured due to failure!");
    }

    driver.quit();
}
🎯 ملخص التحسينات
✔ إصلاح اسم الملف (screenShotName + ".png")
✔ إضافة تعليق توضيحي لكل خطوة
✔ إضافة مثال لاستخدام الدالة في اختبار حقيقي
 */

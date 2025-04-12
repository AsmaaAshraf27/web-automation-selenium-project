package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    public JavascriptExecutor jse;
    public Actions action;
    WebDriverWait wait ;
    public Select select;
    //JavascriptExecutor---> this is an interface
    public BasePage(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }
    public void clickOnButton(WebElement element)
    {
        element.click();
    }
    public void sendText(WebElement element,String value)
    {
        element.sendKeys(value);
    }
    public void scrollToBottom()
    {
        jse.executeScript("scrollBy(0,2500)");
        //jse:
        //ده اختصار غالبًا لـ JavaScriptExecutor، وده interface في Selenium بيسمحلك تشغّلي كود JavaScript جوا صفحة الويب.
        //
        //قبل ما تستخدميه، لازم تكوني عرّفتيه كده:
        //JavascriptExecutor jse = (JavascriptExecutor) driver;
        //executeScript(...):
        //دي الميثود اللي بتخلينا ننفذ كود JavaScript جوه الصفحة.
        //
        //"scrollBy(0,2500)":
        //ده الكود اللي بيتم تشغيله:
        //
        //scrollBy(x, y): معناها: حرّك الصفحة بمقدار x و y بكسل من المكان اللي واقف فيه حاليًا.
        //
        //0: يعني ما تتحركيش يمين أو شمال (أفقيًا).
        //
        //2500: يعني انزلي لتحت 2500 بكسل (رأسيًا).
        //💡 مثال عملي:
        //لو العنصر اللي عايزة توصليه مش ظاهر على الشاشة، ومحتاجه تعملي scroll عشان تشوفيه، الكود ده بيخلي الصفحة تنزل لتحت، وده مهم في حالات زي:
        //
        //العناصر اللي بتكون تحت في الصفحة ومش بتظهر غير بعد الـ scroll.
        //
        //لما العنصر يبقى hidden أو مش في viewport.
        //
        //✅ ملاحظات مهمة:
        //لو الصفحة قصيرة ومفيهاش 2500 بكسل لتحت، مش هتحسي بالفرق.
        //
        //ممكن تغيّري الرقم لأي قيمة على حسب احتياجك.
        //
        //مثلاً:
        //
        //java
        //Copy
        //Edit
        //jse.executeScript("scrollBy(0,500)");  // ينزل 500 بكسل
        //jse.executeScript("scrollBy(0,-500)"); // يطلع لفوق 500 بكسل
        //🔁 بديل ذكي (Scroll لعنصر معين):
        //لو عندك عنصر معين وعايزة تروحي له مباشرة، استخدمي:
        //
        //java
        //Copy
        //Edit
        //WebElement element = driver.findElement(By.id("someId"));
        //jse.executeScript("arguments[0].scrollIntoView(true);", element);
        //ده بيخلي العنصر يظهر في الشاشة تلقائيًا.
        //✅ السطر:
        //java
        //Copy
        //Edit
        //JavascriptExecutor jse = (JavascriptExecutor) driver;
        //✅ معناه ببساطة:
        //أنتي بتقولي لـ Selenium:
        //
        //"أنا عايزة أستخدم كود JavaScript جوه صفحة الويب، وعلشان أقدر أعمل كده، لازم أستخدم JavascriptExecutor."
        //
        //✅ تفصيل كل جزء:
        //الجزء	المعنى
        //JavascriptExecutor	Interface في Selenium بيسمحلك تشغّلي JavaScript code جوه المتصفح.
        //(JavascriptExecutor) driver	دي عملية casting معناها: "حوّل الـ WebDriver بتاعي لكائن يقدر يشغّل JavaScript."
        //jse	ده اسم المتغير اللي هيخزن الـ JavascriptExecutor علشان تستخدميه بعد كده.
        //✅ ليه بنعمل casting هنا؟
        //الـ WebDriver في الأصل مايعرفش يشغل JavaScript، فبنحوّله (cast) لكائن يقدر يستخدم كود JavaScript.
        //
        //✅ مثال استخدام بعد السطر ده:
        //بعد ما تعملي السطر ده، تقدري تكتبي:
        //
        //java
        //Copy
        //Edit
        //jse.executeScript("scrollBy(0,1000)");
        //يعني تنفذي أي كود JavaScript زي الـ scroll، أو تغيري في العنصر، أو ترجعي بيانات من الصفحة.
        //
        //🔁 مثال عملي كامل:
        //java
        //Copy
        //Edit
        //WebDriver driver = new ChromeDriver();
        //driver.get("https://example.com");
        //
        //// كده بتحوّلي الـ driver لـ JavaScriptExecutor
        //JavascriptExecutor jse = (JavascriptExecutor) driver;
        ///// تنزلي لتحت في الصفحة
        //jse.executeScript("scrollBy(0,1000)");


    }
    public void clearElement(WebElement element)
    {
        element.clear();
    }

}

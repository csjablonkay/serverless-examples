from conans import ConanFile, CMake

class PocoPyReuseConan(ConanFile):
    name = "example"
    version = "0.0.1"
    requires = "Poco/1.9.0@pocoproject/stable", "hiredis/0.13.3@conan-cpp/latest"
    generators = "cmake_find_package"
    exports_sources = "CMakeLists.txt", "src/*"
    _cmake = None

    @property
    def cmake(self):
        if self._cmake is None:
            self._cmake = CMake(self)
        return self._cmake


    def build(self):
        self.cmake.configure()
        self.cmake.build()

    def package(self):
        self.copy('*.py*')
        self.copy("*.so")

    def package_info(self) :
        self.env_info.PYTHONPATH.append(self.package_folder)
